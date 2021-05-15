package com.lavanya.web.comparator;

import com.lavanya.web.dto.BookDto;

import java.util.Comparator;

public class BookDtoTitleComparator implements Comparator<BookDto>{

    @Override
    public int compare(BookDto b1, BookDto b2) {
        if(b1.getTitle().compareToIgnoreCase(b2.getTitle())>=1){
            return 1;
        }
        if(b1.getTitle().compareToIgnoreCase(b2.getTitle())==0){
            return 0;
        }
        else{
            return -1;
        }
    }

}
