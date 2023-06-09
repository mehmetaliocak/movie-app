//
//  PersonViewModel.swift
//  Movie
//
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

class PersonViewModel : ObservableObject {
    
    private let personService = PersonService()
    @Published var person: Person? = nil
    @Published var movies = [Movie]()

    @Published var loading = false

    init(id: Int64) {
        getPersonDetails(id: id)
    }
    
    private func getPersonDetails(id: Int64) {
        if loading {
            return
        }
        
        self.loading = true
        Task {
            do {
                async let personResponse = asyncFunction(for: personService.getDetails(id: id))
                async let creditsResponse = asyncFunction(for: personService.getCredits(id: id) )
                let responses = try await (personResponse, creditsResponse)
                self.person = responses.0
                self.movies = responses.1.cast
                self.loading = false
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
    
}
