#!/bin/bash

# Имя процесса java приложения
APP_NAME="stsr3-1.0-SNAPSHOT.jar"

# Проверяем, работает ли приложение
PID=$(pgrep -f "$APP_NAME")

if [ -n "$PID" ]; then
  echo "Останавливаем приложение с PID $PID"
  kill -9 $PID
fi

echo "Запускаем приложение"
nohup java -jar target/$APP_NAME > app.log 2>&1 &
