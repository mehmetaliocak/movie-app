//
//  MovieCellView.swift
//  Movie
//
//

import SwiftUI
import shared

struct MovieCellView : View {
    
    var movie: Movie

    var body: some View {
        ZStack {
            AsyncImage(url: URL(string: movie.posterUrl())) { image in
                image.resizable()
                          
            } placeholder: {
                VStack() {
                    Spacer()
                    ProgressView()
                    Spacer()
                }
            }
            
            VStack {
                Spacer().frame(height: 8)
                
                HStack {
                    Spacer()
                    
                    Text(movie.voteAverage.formatToSingleDigit())
                        .font(.caption)
                        .padding(16)
                        .background(.black.opacity(0.7))
                        .foregroundColor(.white)
                        .clipShape(Circle())
                    
                    Spacer().frame(width: 8)
                }
                
                
                Spacer()
                VStack{
                    Spacer().frame(height: 8)
                    Text(movie.title)
                        .font(.callout)
                        .padding(.horizontal, 4)
                        .lineLimit(1)
                    Text(movie.releaseDate?.formatDate() ?? " - ")
                        .font(.caption)
                        .padding(.horizontal, 4)
                        .lineLimit(1)
                    Spacer().frame(height: 8)
                }
                .frame(minWidth: 0, maxWidth: .infinity)
                .foregroundColor(.white)
                .background(.black.opacity(0.7))
            }
        }
        .aspectRatio(0.5, contentMode: .fit)
        .cornerRadius(16)
    }
}


struct MovieCellView_Previews: PreviewProvider {
    static var previews: some View {
        MovieCellView(
            movie: Movie(
                id: 12,
                title: "Black Adam",
                posterPath: "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
                overview: "Nearly 5,000 years.",
                releaseDate: "1999-09-02",
                voteAverage: 8.8
            )
        )
    }
}
