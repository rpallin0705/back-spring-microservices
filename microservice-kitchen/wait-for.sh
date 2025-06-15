#!/bin/sh

host="$1"
port="$2"
shift 2

until nc -z "$host" "$port"; do
  echo "⏳ Esperando a que $host:$port esté disponible..."
  sleep 1
done

echo "✅ $host:$port disponible, ejecutando comando..."
exec "$@"