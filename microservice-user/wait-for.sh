#!/bin/sh

host="$1"
port="$2"
shift 2
cmd="$@"

echo "⏳ Esperando a $host:$port..."
while ! nc -z "$host" "$port"; do
  sleep 1
done

echo "✅ $host:$port está disponible. Ejecutando comando..."
exec $cmd