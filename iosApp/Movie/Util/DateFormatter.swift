//
//  DateFormatter.swift
//  Movie
//
//  Created by Mehmet Ali Ocak on 14.06.2023.
//

import Foundation

extension String {
    
    func formatDate() -> String? {
        guard let date = getDateFromString(date: self) else { return " - "}

        let dateFormatter = DateFormatter()
        dateFormatter.timeZone = NSTimeZone.local
        dateFormatter.locale = NSLocale.autoupdatingCurrent
        dateFormatter.dateFormat = "dd MMM, yyyy"
        return dateFormatter.string(from: date)
    }
    
    private func getDateFromString(date: String) -> Date? {
        if date.isEmpty {
            return nil
        }
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        return formatter.date(from: date)
      }
}
