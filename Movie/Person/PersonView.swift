//
//  PersonView.swift
//  Movie
//
//

import Foundation
import SwiftUI

struct PersonView: View {
    
    @ObservedObject private var viewModel: PersonViewModel
    
    init(id: Int) {
        viewModel = PersonViewModel(id: id)
    }
    
    var body: some View {
        ScrollView {
            if viewModel.loading {
                ProgressView()
            } else {
                if let person = viewModel.person {
                    VStack(alignment: .leading) {
                        
                        PersonDescriptionView(person: person)
                        
                        Text("Known For")
                            .font(.title3)
                            .padding(8)
                        
                        ScrollView(.horizontal, showsIndicators: false) {
                            LazyHStack {
                                ForEach(viewModel.movies) { movie in
                                    NavigationLink(destination: MovieDetailView(movie: movie)) {
                                        ZStack {
                                            MovieCellView(movie: movie)
                                        }.frame(width: 150)
                                    }
                                }
                            }
                        }
                        .padding(.horizontal, 8)
                        
                        Spacer()
                    }
                }
            }
            
        }

    }
}

private struct PersonDescriptionView: View {
    var person: Person
    
    var body: some View {
        VStack() {
            HStack {
                ZStack {
                    AsyncImage(url: person.profilePhotoURL) { image in
                        image.resizable()
                                  
                    } placeholder: {
                        VStack() {
                            Spacer()
                            ProgressView()
                            Spacer()
                        }
                    }
                    .frame(width: 100, height: 100)
                    .clipShape(Circle())


                }
                
                VStack(alignment: .leading, spacing: 4) {
                    Text(person.name).font(.body)
                    Text(person.placeOfBirth).font(.body)
                    Text(formattDate(date: person.birthday)).font(.body)
                }
                
                Spacer()
            }
            
            Spacer().frame(height: 16)

            LoadMoreTextView(text: person.biography, lineLimit: 5)
            
        }.padding(8)
    }
    
    private func formattDate(date: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd MMM, yyyy"
        return formatter.string(from: date)
    }
}

private struct LoadMoreTextView: View {
    var text: String
    var lineLimit: Int
    @State private var expanded = false
    
    var body: some View {
        VStack {
            Text(text)
                .font(.body)
                .lineLimit(expanded ? Int.max : lineLimit)
            
            Spacer().frame(height: 4)
            
            HStack {
                Spacer()
                Text(expanded ? "Collapse" : "Load more")
                    .font(.body)
                    .foregroundColor(.blue)
                    .underline(true)
                    .onTapGesture {
                        expanded.toggle()
                    }
            }
        }.padding(8)
    }
}

struct PersonView_Previews: PreviewProvider {
    static var previews: some View {
        PersonView(id: 8891)
    }
}
