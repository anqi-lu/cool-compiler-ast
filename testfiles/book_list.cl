# example of static and dynamic type differing for a dispatch

class Book inherits IO {
    title : Str;
    author : Str;

    initBook(title_p : Str, author_p : Str) : Book {
        {
            title <- title_p;
            author <- author_p;
            self;
        }
    };

    print() : Book {
        {
            outStr("title:      ").outStr(title).outStr("\n");
            outStr("author:     ").outStr(author).outStr("\n");
            self;
        }
    };
};

class Article inherits Book {
    per_title : Str;

    initArticle(title_p : Str, author_p : Str,
		per_title_p : Str) : Article {
        {
            initBook(title_p, author_p);
            per_title <- per_title_p;
            self;
        }
    };

    print() : Book {
        {
	    self.print();
            outStr("periodical:  ").outStr(per_title).outStr("\n");
            self;
        }
    };
};

class BookList inherits IO { 
    (* Since abort "returns" type Object, we have to add
       an expression of type Bool here to satisfy the typechecker.
       This code is unreachable, since abort() halts the program.
    *)
    isNil() : Bool { { abort(); true; } };
    
    cons(hd : Book) : Cons {
        (let new_cell : Cons <- new Cons in
            new_cell.init(hd,self)
        )
    };

    (* Since abort "returns" type Object, we have to add
       an expression of type Book here to satisfy the typechecker.
       This code is unreachable, since abort() halts the program.
    *)
    car() : Book { { abort(); new Book; } };
    
    (* Since abort "returns" type Object, we have to add
       an expression of type BookList here to satisfy the typechecker.
       This code is unreachable, since abort() halts the program.
    *)
    cdr() : BookList { { abort(); new BookList; } };
    
    print_list() : Object { abort() };
};

class Cons inherits BookList {
    xcar : Book;  # We keep the car and cdr in attributes.
    xcdr : BookList; # Because methods and features must have different names,
    # we use xcar and xcdr for the attributes and reserve
    # car and cdr for the features.
    
    isNil() : Bool { false };
    
    init(hd : Book, tl : BookList) : Cons {
        {
            xcar <- hd;
            xcdr <- tl;
            self;
        }
    };

    car() : Book { xcar };

    cdr() : BookList { xcdr };
    
    print_list() : Object {
        {
            case xcar.print() of
                dummy : Book => outStr("- dynamic type was Book -\n");
                dummy : Article => outStr("- dynamic type was Article -\n");
            esac;
            xcdr.print_list();
        }
    };
};

class Nil inherits BookList {
    isNil() : Bool { true };

    print_list() : Object { true };
};


class Main {

    books : BookList;

    main() : Object {
        (let a_book : Book <-
            (new Book).initBook("Compilers, Principles, Techniques, and Tools",
                                "Aho, Sethi, and Ullman")
        in
            (let an_article : Article <-
                (new Article).initArticle("The Top 100 CD_ROMs",
                                          "Ulanoff",
                                          "PC Magazine")
            in
                {
                    books <- (new Nil).cons(a_book).cons(an_article);
                    books.print_list();
                }
            )  # end let an_article
        )  # end let a_book
    };
};