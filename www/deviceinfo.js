cordova.define("com.mosalingua.plugin.deviceinfo.DeviceInfo", function(require, exports, module) {

    var DeviceInfo = {
        getSerialNumber: function(successCallback, errorCallback) {
            cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'DeviceInfo', // mapped to our native Java class
                'getSerialNumber', // with this action name
                []
            );
        },

        getIMEI: function (successCallback,errorCallback) {

            cordova.exec(
                successCallback,
                errorCallback,
                'DeviceInfo',
                'getIMEI',
                []
            );
        },

        //get both IMEI and Serial Number in one shot
        getDeviceInfo: function (successCallback,errorCallback) {

            cordova.exec(
                successCallback,
                errorCallback,
                'DeviceInfo',
                'getDeviceInfo',
                []
            );
        }
    };

    module.exports = DeviceInfo;

});