//
//  PopularMoviesViewModel.swift
//  Movie
//
//

import Foundation
import Alamofire

class PopularMoviesViewModel : ObservableObject {
    
    private let movieService = MovieService()
    private var page: Int = 1
    @Published var movies = [Movie]()
    @Published var loading = false
    
    init() {
        getMovies()
    }
    
    private func getMovies() {
        if loading {
            return
        }
        
        self.loading = true
        Task {
            let response = try await movieService.getPopularMovies(page: page)
            DispatchQueue.main.async {
                self.movies += response.results
                self.loading = false
                self.page += 1
            }
        }
    }
    
    func onMovieAppear(movie: Movie) {
        guard let lastMovie = movies.last else { return }
        if lastMovie.id == movie.id {
            getMovies()
        }
    }
    
}
