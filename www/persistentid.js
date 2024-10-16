// File: www/persistentId.js

var exec = require("cordova/exec");

var PersistentId = {
  getUniqueId: function (success, error) {
    exec(success, error, "PersistentIdPlugin", "getAndroidId", []);
  },
};

module.exports = PersistentId;
