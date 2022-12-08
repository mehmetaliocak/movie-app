//
//  MovieDetailViewModel.swift
//  Movie
//
//

import Foundation

class MovieDetailViewModel: ObservableObject {

    private let movieService = MovieService()
    private let creditsService = CreditsService()

    var movie: Movie
    @Published var movieDetail: MovieDetail? = nil
    @Published var casts = [Cast]()

    @Published var loading = false
    
    init(movie: Movie) {
        self.movie = movie
        getMovieDetail(id: movie.id)
    }
    
    private func getMovieDetail(id: Int) {
        if loading {
            return
        }
        
        self.loading = true
        Task {
            async let movieDetailResponse = movieService.getMovieDetail(id: id)
            async let creditsResponse = creditsService.getCredits(id: id)
            let responses = try await (movieDetailResponse, creditsResponse)
            
            DispatchQueue.main.async {
                self.movieDetail = responses.0
                self.casts = responses.1.cast
                self.loading = false
            }
        }
    }
}
