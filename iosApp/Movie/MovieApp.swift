//
//  MovieApp.swift
//  Movie
//
//

import SwiftUI
import shared

@main
struct MovieApp: App {
    
    init() {
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            NavigationView {
                PopularMoviesView()
            }
        }
    }
}
