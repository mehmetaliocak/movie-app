//
//  PersonViewModel.swift
//  Movie
//
//

import Foundation

class PersonViewModel : ObservableObject {
    
    private let personService = PersonService()
    @Published var person: Person? = nil
    @Published var movies = [Movie]()

    @Published var loading = false

    init(id: Int) {
        getPersonDetails(id: id)
    }
    
    private func getPersonDetails(id: Int) {
        if loading {
            return
        }
        
        self.loading = true
        Task {
            async let personDetailResponse = personService.getDetails(id: id)
            async let creditsResponse = personService.getCredits(id: id)
            do {
                let responses = try await (personDetailResponse, creditsResponse)
                
                DispatchQueue.main.async {
                    self.person = responses.0
                    self.movies = responses.1.cast
                    self.loading = false
                }
            } catch {
                debugPrint(id)
                debugPrint(error)
            }
     
        }
    }
    
}
