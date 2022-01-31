let CurrencyConverterApp = angular.module('CurrencyConverterApp', []);

CurrencyConverterApp.controller('CurrencyController', function ($scope, $http) {

    $http.get("http://localhost:8080/api/v1/currency/all")
        .then(resp => {
                $scope.currencyList = resp.data;

                console.log($scope.currencyList);
            },
            resp => {
                console.error(resp);
            });

    $http.get("http://localhost:8080/api/v1/currency/all/history")
         .then(resp => {
                 $scope.currencyListHistory = resp.data;

                 console.log($scope.currencyListHistory);
            },
            resp => {
                console.error(resp);
             });

    $scope.currency1_sum = '';
    $scope.currency2_sum = '';

    $scope.currency1_charcode = '';
    $scope.currency2_charcode = '';

    var currentDateValue='';
    var currentTimeValue='';
    getDate();
    $scope.date_exchange = currentDateValue + "/" + currentTimeValue;
    $scope.status_exchange= 'исполнено';

    $scope.course= '';

    $scope.currencyhistory = '';

    $scope.create = function (currency1_sum,currency2_sum,currency1_charcode,currency2_charcode,date_exchange,status_exchange,course) {

        if (currency2_sum!=="") {

            $http.post("http://localhost:8080/api/v1/currency/history", {
                'currency1Sum': currency1_sum,
                'currency2Sum': currency2_sum,
                'currency1Charcode': currency1_charcode,
                'currency2Charcode': currency2_charcode,
                'statusExchange': status_exchange,
                'dateExchange': date_exchange,
                'course': course

            })
                //    $http.post("http://localhost:8080/api/v1/currency", {'currency1_charcode:\'frontend\',currency2_charcode:\'null\',course:\'null\',currency1_sum:\'null\',currency2_sum:\'null\',status_exchange:\'null\',date_exchange':currency1_sum})
                .then(resp => {
                        $scope.currencyListHistory.push(resp.data);
                        console.log($scope.currencyListHistory);

                    },
                    resp => {
                        console.error(resp);
                    });

            $scope.currency1_sum = '';
        }
    }

    $scope.delete = function (currencyhistory) {
        $http.delete("http://localhost:8080/api/v1/currency/history/" + currencyhistory.id)
            .then(resp => {
                    let ix = $scope.currencyListHistory.map(currencyhistory => currencyhistory.id).indexOf(currencyhistory.id);
                    $scope.currencyListHistory.splice(ix, 1);
                    console.log($scope.currencyListHistory);

                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.select = function (currency) {
        $http.select("http://localhost:8080/api/v1/currency/" + currency.id)
            .then(resp => {
                    let ix = $scope.currencyList.map(currency => currency.id).indexOf(currency.id);
                   // $scope.currencyList.splice(ix, 1);
                    console.log(ix);

                },
                resp => {
                    console.error(resp);
                });
    }

    function getDate()
    {
        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth()+1;
        var year = date.getFullYear();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();

        if(seconds < 10)
        {
            seconds = '0' + seconds;
        }
        if(minutes < 10)
        {
            minutes = '0' + minutes;
        }
        if(month < 10)
        {
            month = '0' + month;
        }

        currentDateValue = day + "." + month + "." + year;
        currentTimeValue = hours + ':' + minutes + ':' + seconds;
        // document.getElementById('datedisplay').innerHTML = "Сегодня: " + day + "." + month + "." + year;
        // document.getElementById('timedisplay').innerHTML = "Текущее время: " + hours + ':' + minutes + ':' + seconds;
    }
    setInterval(getDate, 0);

})


function validate(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /[0-9]|\./;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) {
            theEvent.preventDefault();
            var exchangeValue = theEvent.preventDefault() / 2;
            document.getElementById("exchangeValue").innerHTML = exchangeValue;
        }

    }
}
