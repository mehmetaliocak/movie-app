//
//  Person.swift
//  Movie
//
//

import Foundation

struct CreditsResponse: Codable {
    let cast: [Cast]
}

struct Cast: Codable, Identifiable  {
    let id: Int
    let name: String
    let character: String
    let profilePath: String?

    
    var profilePhotoURL: URL {
        return URL(string: "https://image.tmdb.org/t/p/original\(profilePath ?? "")")!
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case character
        case profilePath = "profile_path"
    }
}
