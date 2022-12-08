//
//  Movie.swift
//  Movie
//
//

import Foundation

struct MoviesResponse: Codable {
    let page: Int
    let total_results: Int
    let total_pages: Int
    let results: [Movie]
}

struct Movie: Codable, Identifiable  {
    let id: Int
    let title: String
    let posterPath: String?
    let overview: String
    let releaseDate: String?
    let voteAverage: Double
    
    var posterURL: URL {
        return URL(string: "https://image.tmdb.org/t/p/original\(posterPath ?? "")")!
    }
    
    var voteAverageText: String {
        return String(format: "%.1f", voteAverage)
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case title
        case posterPath = "poster_path"
        case overview
        case releaseDate = "release_date"
        case voteAverage = "vote_average"
    }
}
