#!/bin/bash

host="$1"
port="$2"

echo "⏳ Esperando a que $host:$port esté disponible..."

while ! nc -z "$host" "$port"; do
  sleep 1
done

echo "✅ $host:$port disponible, iniciando app..."
exec "${@:3}"
