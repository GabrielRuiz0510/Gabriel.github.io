# -*- coding: utf-8 -*-
"""
Created on Thu Jan 26 13:54:03 2023

@author: Gabriel Ruiz
"""
import matplotlib.pyplot as plt
import numpy as np
from sentence_transformers import SentenceTransformer
import pandas as pd
import seaborn as sns
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.decomposition import PCA
import random

class main:
    def __init__(self):
        plt.style.use('ggplot')
        plt.rcParams['font.family'] = 'sans-serif' 
        plt.rcParams['font.serif'] = 'Ubuntu' 
        plt.rcParams['font.monospace'] = 'Ubuntu Mono' 
        plt.rcParams['font.size'] = 14 
        plt.rcParams['axes.labelsize'] = 12 
        plt.rcParams['axes.labelweight'] = 'bold' 
        plt.rcParams['axes.titlesize'] = 12 
        plt.rcParams['xtick.labelsize'] = 12 
        plt.rcParams['ytick.labelsize'] = 12 
        plt.rcParams['legend.fontsize'] = 12 
        plt.rcParams['figure.titlesize'] = 12 
        plt.rcParams['image.cmap'] = 'jet' 
        plt.rcParams['image.interpolation'] = 'none' 
        plt.rcParams['figure.figsize'] = (12, 10) 
        plt.rcParams['axes.grid']=True
        plt.rcParams['lines.linewidth'] = 2 
        plt.rcParams['lines.markersize'] = 8
        colors = ['xkcd:pale orange', 'xkcd:sea blue', 'xkcd:pale red', 'xkcd:sage green', 'xkcd:terra cotta', 'xkcd:dull purple', 'xkcd:teal', 'xkcd: goldenrod', 'xkcd:cadet blue',
        'xkcd:scarlet']
        
        #%%
        global data
        global X
        global cos_sim_data
        
        data = pd.read_csv('imdb_top_1000.csv')
        #X = np.array(data.Overview)
        X = data["Overview"].tolist()
        data = data[['Genre', 'Overview', 'Series_Title']]
        data.head()
        
        text_data = X
        model = SentenceTransformer('all-MiniLM-L6-v2')
        embeddings = model.encode(text_data, show_progress_bar=True)
        
        #%%
        embed_data = embeddings
        X = np.array(embed_data)
        n_comp = 5
        pca = PCA(n_components = n_comp)
        pca.fit(X)
        pca_data = pd.DataFrame(pca.transform(X))
        pca_data.head()
        
        
        #%%
        sns.pairplot(pca_data)
    
    #%%
        cos_sim_data = pd.DataFrame(cosine_similarity(X))
    def give_recommendations(self, title = '',print_recommendation = False, print_recommendation_plots = False, print_genres = False):
        index = 0
        index = self.find_title(title)
        index_recomm = cos_sim_data.loc[index].sort_values(ascending = False).index.tolist()[1:6]
        movies_recomm = data['Series_Title'].loc[index_recomm].values
        movieList = ''
        plotList = ''
        genreList = ''
        #result = {'Movies':movies_recomm, 'Index' :index_recomm}
        if print_recommendation == True:
            print('The movie entered is: %s \n' %(data['Series_Title'].loc[index]))
            movieList = 'The movie entered is:\n' + data['Series_Title'].loc[index] + "\n"
            k = 1
            for movie in movies_recomm:
                print("The number %i recommended movie is: \n %s \n" %(k, movie))
                movieList += "\n"
                movieList += "The number " + str(k) + " recommended movie is:\n" + movie + "\n"
                k = k + 1
        if print_recommendation_plots == True:
            print("The plot of the movie entered is:\n %s \n" %(data['Overview'].loc[index]))  
            plotList = "The plot of the movie entered is:\n" + data['Overview'].loc[index] + "\n"
            k = 1
            for q in range(len(movies_recomm)):
                plot_q = data['Overview'].loc[index_recomm[q]]
                movie_q = data['Series_Title'].loc[index_recomm[q]]
                print('the plot of %s is:\n %s \n' %(movie_q, plot_q))
                plotList += "\n"
                plotList += "the plot of " + movie_q  + " is:\n" + plot_q + "\n"
               # print('The plot of the number %i recommended movie is this one:\n %s \n' %(k, plot_q))
                k = k + 1
        if print_genres == True:
            print('The genre(s) of the movie entered are:\n %s \n'%(data['Genre'].loc[index]))
            genreList = "The genre(s) of the movie entered are:\n" + data['Genre'].loc[index] + "\n"
            k = 1
            for q in range(len(movies_recomm)):
                genre_q = data['Genre'].loc[index_recomm[q]]
                movie_q = data['Series_Title'].loc[index_recomm[q]]
                print('the genre(s) of %s are:\n %s \n' %(movie_q, genre_q))
                genreList += "\n"
                genreList += "the genre(s) of " + movie_q + "are:\n" + genre_q + "\n"
                #print('The genre of the number %i recommended is this one:\n %s \n' %(k,plot_q))
                k = k + 1
        return [movieList, plotList, genreList]
    #%%
    def random_movie(self):
        num = random.randint(0, 999)
        return self.give_recommendations(data['Series_Title'].loc[num], True, True)
        
    #%%
    def find_title(self, title):
        indexNum = 0
        while indexNum < len(data.index):
            if title.casefold() == data['Series_Title'].loc[indexNum].casefold():
                return indexNum
            elif title.casefold() in data['Series_Title'].loc[indexNum].casefold():
                return indexNum
            else:
                indexNum += 1
        return indexNum
        
#%%
if __name__ == '__main__':
    main()