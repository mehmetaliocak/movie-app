//
//  DateDecoder.swift
//  Movie
//
//

import Foundation

class DateDecoder: JSONDecoder {
    
    let formatter = DateFormatter()
    
    override init() {
        super.init()
        formatter.dateFormat = "yyyy-MM-dd"
        dateDecodingStrategy = .formatted(formatter)
    }
}
