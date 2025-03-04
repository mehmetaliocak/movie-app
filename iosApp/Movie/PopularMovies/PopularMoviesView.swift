//
//  PopularMoviesView.swift
//  Movie
//
//

import SwiftUI
import shared

struct PopularMoviesView: View {
    
    let columns: [GridItem] = [
        GridItem(.adaptive(minimum: 125, maximum: 200), spacing:16)
    ]
    
    @StateObject private var viewModel = PopularMoviesViewModel()
    
    var body: some View {
        ScrollView {
            VStack {
                LazyVGrid(columns: columns, spacing: 16) {
                    ForEach(viewModel.movies, id: \.id) { movie in
                        
                        NavigationLink(
                            destination: MovieDetailView(movie: movie)
                        ) {
                            MovieCellView(movie: movie)
                                .onAppear() {
                                    viewModel.onMovieAppear(movie: movie)
                                }
                        }
                    }
                }.padding(16)
                
                if (viewModel.loading) {
                    ProgressView()
                }
            }
        }

    }
}

struct PopularMoviesView_Previews: PreviewProvider {
    static var previews: some View {
        PopularMoviesView()
    }
}
