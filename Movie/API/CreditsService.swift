//
//  CreditsService.swift
//  Movie
//
//

import Foundation
import Alamofire

class CreditsService {
    
    func getCredits(id: Int) async throws -> CreditsResponse {
        return try await AF.request(
            "\(ApiConstants.baseUrl)/movie/\(id)/credits?api_key=\(ApiConstants.apiKey)"
        )
        .serializingDecodable(CreditsResponse.self)
        .value
    }
}

