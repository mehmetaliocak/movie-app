//
//  MovieService.swift
//  Movie
//
//

import Foundation
import Alamofire

class MovieService {
    
    func getPopularMovies(page: Int) async throws -> MoviesResponse {
        return try await AF.request(
            "\(ApiConstants.baseUrl)/movie/popular?api_key=\(ApiConstants.apiKey)&page=\(page)"
        )
        .serializingDecodable(MoviesResponse.self, decoder: DateDecoder())
        .value
    }
    
    func getMovieDetail(id: Int) async throws -> MovieDetail {
        return try await AF.request(
            "\(ApiConstants.baseUrl)/movie/\(id)?api_key=\(ApiConstants.apiKey)"
        )
        .serializingDecodable(MovieDetail.self, decoder: DateDecoder())
        .value
    }
}
