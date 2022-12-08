//
//  PersonService.swift
//  Movie
//
//

import Foundation
import Alamofire

class PersonService {
    
    func getDetails(id: Int) async throws -> Person {
        return try await AF.request(
            "\(ApiConstants.baseUrl)/person/\(id)?api_key=\(ApiConstants.apiKey)"
        )
        .serializingDecodable(Person.self, decoder: DateDecoder())
        .value
    }
    
    func getCredits(id: Int) async throws -> PersonCreditsResponse {
        return try await AF.request(
            "\(ApiConstants.baseUrl)/person/\(id)/movie_credits?api_key=\(ApiConstants.apiKey)"
        )
        .serializingDecodable(PersonCreditsResponse.self, decoder: DateDecoder())
        .value
    }
}
