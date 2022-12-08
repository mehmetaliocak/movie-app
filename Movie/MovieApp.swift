//
//  MovieApp.swift
//  Movie
//
//

import SwiftUI

@main
struct MovieApp: App {
    var body: some Scene {
        WindowGroup {
            NavigationView {
                PopularMoviesView()
            }
        }
    }
}
