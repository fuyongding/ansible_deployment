FROM python:3.8-slim

WORKDIR /app

RUN pip install flask 
RUN pip install requests

# copy to /app directory
COPY app.py ./
COPY templates ./templates/

RUN mkdir static

CMD ["python", "app.py"]
