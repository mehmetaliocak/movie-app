//
//  PopularMoviesViewModel.swift
//  Movie
//
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

class PopularMoviesViewModel : ObservableObject {
    
    private let movieService = MovieService()
    private var page: Int32 = 1
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
            do {
                let response = try await asyncFunction(for: movieService.getPopularMovies(page: page))
                
                DispatchQueue.main.async {
                    self.movies += response.results
                    self.loading = false
                    self.page += 1
                }
            } catch {
                print("Failed with error: \(error)")
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
