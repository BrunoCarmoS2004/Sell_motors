#!/bin/bash

set -e

echo "Updating MongoDB users."

mongosh --username "$MONGO_INITDB_ROOT_USERNAME" --password "$MONGO_INITDB_ROOT_PASSWORD" --authenticationDatabase admin <<EOF
use('sell-motors-query');

db.dropUser('sell-motors-query');
db.dropUser('sync');

db.createUser({
  user: 'sync',
  pwd: 'sync',
  roles: [{ role: 'dbOwner', db: 'sell-motors-query' }]
});

db.createUser({
  user: 'sell-motors-query',
  pwd: 'sell-motors-query',
  roles: [{ role: 'read', db: 'sell-motors-query' }]
});
EOF

echo "MongoDB user update completed."