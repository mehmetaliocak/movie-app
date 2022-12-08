//
//  MovieCellView.swift
//  Movie
//
//

import SwiftUI

struct MovieCellView : View {
    
    var movie: Movie

    var body: some View {
        ZStack {
            AsyncImage(url: movie.posterURL) { image in
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
                    
                    Text(movie.voteAverageText)
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
                    Text(formattedDate)
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
    
    var formattedDate: String {
        guard let date = formatToDate(date: movie.releaseDate) else { return " - "}
        let formatter = DateFormatter()
        formatter.dateFormat = "dd MMM, yyyy"
        return formatter.string(from: date)
    }
    
    private func formatToDate(date: String?) -> Date? {
        guard let date = date else { return nil }
        if date.isEmpty {
            return nil
        }
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        return formatter.date(from: date)
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
                releaseDate: "1999-02-02",
                voteAverage: 8.8
            )
        )
    }
}
