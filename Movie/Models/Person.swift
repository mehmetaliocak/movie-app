//
//  Person.swift
//  Movie
//
//

import Foundation

struct Person: Codable, Identifiable  {
    let id: Int
    let name: String
    let placeOfBirth: String
    let biography: String
    let birthday: Date
    let profilePath: String?

    
    var profilePhotoURL: URL {
        return URL(string: "https://image.tmdb.org/t/p/original\(profilePath ?? "")")!
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case placeOfBirth = "place_of_birth"
        case biography
        case birthday
        case profilePath = "profile_path"
    }
}

