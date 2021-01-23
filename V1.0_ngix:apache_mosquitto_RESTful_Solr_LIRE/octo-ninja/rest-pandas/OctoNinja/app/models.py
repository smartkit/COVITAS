from django.db import models

# Create your models here.
class TimeSeries(models.Model):
    date = models.DateField()
    value = models.FloatField()