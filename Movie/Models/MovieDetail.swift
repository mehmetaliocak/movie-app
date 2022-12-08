//
//  MovieDetail.swift
//  Movie
//
//

import Foundation

struct MovieDetail: Codable, Identifiable  {
    let id: Int
    let title: String
    let genres: [Genre]
    let backdropPath: String?
    let overview: String
    let releaseDate: Date
    let voteAverage: Double
    
    var backdropURL: URL {
        return URL(string: "https://image.tmdb.org/t/p/original\(backdropPath ?? "")")!
    }
    
    var voteAverageText: String {
        return String(format: "%.1f", voteAverage)
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case title
        case genres
        case backdropPath = "backdrop_path"
        case overview
        case releaseDate = "release_date"
        case voteAverage = "vote_average"
    }
}

struct Genre: Codable, Identifiable {
    let id: Int
    let name: String
}
