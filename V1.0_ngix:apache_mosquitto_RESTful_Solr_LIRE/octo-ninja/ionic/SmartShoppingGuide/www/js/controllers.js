angular.module('starter.controllers', [])

    .controller('MainCtrl', function ($scope, $http, $rootScope, $location, $ionicModal, $ionicLoading, $ionicNavBarDelegate,
                                      CONFIG_ENV, $log, $cordovaToast) {

        // Form data for the login modal
        $scope.loginData = {};

        ///LoginModal
        $ionicModal.fromTemplateUrl('templates/modal-login.html', {
            scope: $scope,
            backdropClickToClose: false
        }).then(function (modal) {
//        console.log("modal-login.html init!!!");
            $scope.loginModal = modal;
            $scope.loginModal.user = {
                username: "demo",
                password: "demo"
            };
            //Login Modal
            if (window.localStorage['auth_ssg']) {
                $scope.loginModal.hide();
            } else {
//     $urlRouterProvider.otherwise('/login');
                $scope.loginModal.show();
            }
        });

        // Triggered in the login modal to close it
        $scope.closeLogin = function () {
            $scope.loginModal.hide();
        };

        // Open the login modal
        $scope.login = function () {
            $scope.loginModal.show();
        };

        // Perform the login action when the user submits the login form
        $scope.doLogin = function () {
            console.log('Doing login', $scope.loginData);

            // Simulate a login delay. Remove this and replace with your login
            // code if using a login system
            $timeout(function () {
                $scope.closeLogin();
            }, 1000);
        };
    })
    .controller('DashCtrl', function ($scope) {
    })

    .controller('ServesCtrl', function ($scope, ServeService) {
        $scope.serves = ServeService.all();
    })

    .controller('ServeDetailCtrl', function ($scope, $stateParams, ServeService) {
        $scope.serve = ServeService.get($stateParams.serveId);
    })

    .controller('AccountCtrl', function ($scope) {
    });
