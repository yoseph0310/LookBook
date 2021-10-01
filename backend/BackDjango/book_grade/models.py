from django.db import models
from book.models import Book
from user.models import User

class BookGrade(models.Model):
    book_grade_id = models.AutoField(primary_key=True)
    book_isbn = models.ForeignKey(Book, related_name="book", on_delete=models.CASCADE, db_column='book_isbn')
    user_email = models.ForeignKey(User, related_name="user", on_delete=models.CASCADE, db_column='user_email')
    book_grade = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'Book_Grade'





