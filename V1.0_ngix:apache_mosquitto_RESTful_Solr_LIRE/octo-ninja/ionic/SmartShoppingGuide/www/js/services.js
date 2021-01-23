angular.module('starter.services', [])

/**
 * A simple example service that returns some data.
 */
    .factory('ServeService', function () {
        // Might use a resource here that returns a JSON array

        // Some fake testing data
        var serves = [
            {
                id: 0, gname: '服务区(1)', data: [
                {id: 0, name: '请求1'}]
            },
            {
                id: 1, gname: '服务区(2)', data: [
                {id: 0, name: '请求2'}
            ]
            }
        ];

        return {
            all: function () {
                return serves;
            },
            get: function (serveId) {
                // Simple index lookup
                return serves[serveId];
            }
        }
    });
;
