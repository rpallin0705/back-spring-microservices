#!/bin/bash

set -e

function wait_for_check() {
  local name=$1
  local host=$2
  local port=$3
  local path="/check"
  local url="http://$host:$port$path"

  echo "⏳ Esperando a $name en $url..."
  until curl -s "$url" | grep -q 'OK'; do
    echo "❌ $name aún no responde correctamente..."
    sleep 5
  done
  echo "✅ $name está listo"
}

wait_for_check "Auth Service" auth-service 8094
wait_for_check "Product Service" product-service 8090
wait_for_check "User Service" user-service 8092
wait_for_check "Order Service" order-service 8091
wait_for_check "Kitchen Service" kitchen-service 8093

exec "$@"