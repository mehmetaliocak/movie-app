//
//  MovieDetailView.swift
//  Movie
//
//

import SwiftUI

struct MovieDetailView: View {
    
    @ObservedObject private var viewModel: MovieDetailViewModel
    
    init(movie: Movie) {
        viewModel = MovieDetailViewModel(movie: movie)
    }
    
    var body: some View {
        ScrollView {
            if viewModel.loading {
                ProgressView()
            } else {
                if let movieDetail = viewModel.movieDetail {
                    VStack(alignment: .leading) {
                        MovieDescriptionView(movieDetail: movieDetail)
                        Text("Cast")
                            .font(.title3)
                            .padding(8)
                        ScrollView(.horizontal, showsIndicators: false) {
                            LazyHStack {
                                ForEach(viewModel.casts) { cast in
                                    NavigationLink(destination: PersonView(id: cast.id)) {
                                        CastCellView(cast: cast)
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

private struct MovieDescriptionView: View {
    var movieDetail: MovieDetail
    
    var body: some View {
        VStack {
            ZStack {
                AsyncImage(url: movieDetail.backdropURL) { image in
                    image.resizable()
                              
                } placeholder: {
                    VStack() {
                        Spacer()
                        ProgressView()
                        Spacer()
                    }
                }
                .aspectRatio(1.5, contentMode: .fit)

            }
            
            Spacer().frame(height: 16)

            VStack(alignment: .leading, spacing: 8) {
                
                Text("\(movieDetail.title) - \(movieDetail.voteAverageText)")
                    .font(.title3)
                
                Text(formattDate(date: movieDetail.releaseDate))
                    .font(.body)
                
                Spacer().frame(height: 4)
                
                HStack(spacing: 8) {
                    ForEach(movieDetail.genres) { genre in
                        Text(genre.name)
                            .font(.caption2)
                            .padding(8)
                            .background(Color.orange)
                            .cornerRadius(16)
                    }
                }
                
                Spacer().frame(height: 4)

                Text(movieDetail.overview)
                    .font(.body)
                
                
            }.padding(.horizontal, 8)
        
        }
    }
    
    private func formattDate(date: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd MMM, yyyy"
        return formatter.string(from: date)
    }
}

private struct CastCellView: View {
    var cast: Cast
    
    var body: some View {
        VStack(spacing: 8) {
            ZStack {
                AsyncImage(url: cast.profilePhotoURL) { image in
                    image.resizable()
                } placeholder: {
                    VStack() {
                        Spacer()
                        ProgressView()
                        Spacer()
                    }
                }
                .aspectRatio(0.75, contentMode: .fit)

            }
            
            Text(cast.name)
                .font(.body)
                .padding(.horizontal, 8)
            
            Text(cast.character)
                .font(.caption)
                .padding(.horizontal, 8)
            
            Spacer().frame(height: 4)
        }
        .foregroundColor(.black)
        .frame(width: 200)
        .cornerRadius(16)
        .overlay(
            RoundedRectangle(cornerRadius: 16)
                .stroke(.gray, lineWidth: 0.5)
        )
    }
    
    private func formattDate(date: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd MMM, yyyy"
        return formatter.string(from: date)
    }
}

struct MovieDetailView_Previews: PreviewProvider {
    static var previews: some View {
        MovieDetailView(
            movie: Movie(
                id: 12,
                title: "Black Adam",
                posterPath: "/xdmmd437QdjcCls8yCQxrH5YYM4.jpg",
                overview: "Nearly 5,000 years ",
                releaseDate: "1999-02-02",
                voteAverage: 8.8
            )
        )
    }
}
