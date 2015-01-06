///**
// * Created by user on 06.01.2015.
// */
//angular.module('spinnerService', [])
//    .config(function ($httpProvider) {
//        $httpProvider.interceptors.push('myHttpInterceptor');
//        var spinnerFunction = function (data, headersGetter) {
//            // todo start the spinner here
//            //alert('start spinner');
//            $('#mydiv').show();
//            return data;
//        };
//        $httpProvider.defaults.transformRequest.push(spinnerFunction);
//    })
//// register the interceptor as a service, intercepts ALL angular ajax http calls
//    .factory('myHttpInterceptor', function ($q, $window) {
//        return {
//            // optional method
//            'request': function (config) {
//                // do something on success
//                return config;
//            },
//
//            // optional method
//            'requestError': function (rejection) {
//                // do something on error
//                if (canRecover(rejection)) {
//                    return responseOrNewPromise
//                }
//                return $q.reject(rejection);
//            },
//
//
//            // optional method
//            'response': function (response) {
//                // do something on success
//                $('#mydiv').hide();
//                return response;
//            },
//
//            // optional method
//            'responseError': function (rejection) {
//                // do something on error
//                if (canRecover(rejection)) {
//                    return responseOrNewPromise
//                }
//                $('#mydiv').hide();
//                return $q.reject(response);
//            }
//        }
//    });