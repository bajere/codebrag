angular.module('codebrag.events', []).constant('events', {

    loginRequired: 'codebrag:loginRequired',
    loggedIn: 'codebrag:loggedIn',
    httpError: 'codebrag:httpError',
    authError: 'codebrag:authError',

    commitCountChanged: 'codebrag:totalCommitCountChanged',
    followupCountChanged: 'codebrag:followupCountChanged',

    closeForm: 'codebrag:closeForm',
    scrollOnly: 'codebrag:scrollOnly',

    moreCommitsLoaded: 'codebrag:moreCommitsLoaded'
});