# views.py
from rest_pandas import PandasView
from .models import TimeSeries
class TimeSeriesView(PandasView):
    model = TimeSeries

    # In response to get(), the underlying Django REST Framework ListAPIView
    # will load the default queryset (self.model.objects.all()) and then pass
    # it to the following function.

    def filter_queryset(self, qs): 
        # At this point, you can filter queryset based on self.request or other
        # settings (useful for limiting memory usage)
        return qs

    # Then, the included PandasSerializer will serialize the queryset into a
    # simple list of dicts (using the DRF ModelSerializer).  To customize
    # which fields to include, subclass PandasSerializer and set the
    # appropriate ModelSerializer options.  Then, set the serializer_class
    # property on the view to your PandasSerializer subclass.

    # Next, the PandasSerializer will load the ModelSerializer result into a
    # DataFrame and pass it to the following function on the view.

    def transform_dataframe(self, dataframe):
        # Here you can transform the dataframe based on self.request
        # (useful for pivoting or computing statistics)
        return dataframe

    # Finally, the included Renderers will process the dataframe into one of
    # the output formats below.