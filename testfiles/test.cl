
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