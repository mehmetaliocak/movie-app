//
//  MovieDetailViewModel.swift
//  Movie
//
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

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
    
    private func getMovieDetail(id: Int64) {
        if loading {
            return
        }
        
        self.loading = true
        Task {
            async let movieDetailResponse = asyncFunction(for: movieService.getMovieDetail(id: id))
            async let creditsResponse = asyncFunction(for: creditsService.getCredits(id: id))
            do {
                let responses = try await (movieDetailResponse, creditsResponse)
                self.movieDetail = responses.0
                self.casts = responses.1.cast
                self.loading = false
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
}
