//
//  Double.swift
//  Movie
//
//  Created by Mehmet Ali Ocak on 17.06.2023.
//

import Foundation

extension Double {
    
    func formatToSingleDigit() -> String {
        return String(format: "%.1f", self)
    }

}
