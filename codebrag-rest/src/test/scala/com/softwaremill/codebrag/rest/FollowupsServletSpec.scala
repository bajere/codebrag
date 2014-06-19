package com.softwaremill.codebrag.rest

import com.softwaremill.codebrag.AuthenticatableServletSpec
import com.softwaremill.codebrag.service.user.Authenticator
import org.scalatra.auth.Scentry
import com.softwaremill.codebrag.service.data.UserJson
import org.bson.types.ObjectId
import org.mockito.Mockito._
import com.softwaremill.codebrag.service.followups.FollowupService
import com.softwaremill.codebrag.service.user.UserJsonBuilder._
import com.softwaremill.codebrag.dao.finders.followup.FollowupFinder
import com.softwaremill.codebrag.usecases.FollowupDoneUseCase

class FollowupsServletSpec extends AuthenticatableServletSpec {

  val currentUser = someUser
  var followupFinder = mock[FollowupFinder]
  var useCase = mock[FollowupDoneUseCase]

  override def beforeEach {
    super.beforeEach
    addServlet(new TestableFollowupsServlet(fakeAuthenticator, fakeScentry, followupFinder, useCase), "/*")
  }

  "GET /" should "call backend for list of followups for authenticated user" in {
    userIsAuthenticatedAs(currentUser)
    get("/") {
      status should be (200)
      verify(followupFinder).findAllFollowupsByCommitForUser(new ObjectId(currentUser.id))
    }
  }

}

class TestableFollowupsServlet(fakeAuthenticator: Authenticator, fakeScentry: Scentry[UserJson], followupFinder: FollowupFinder, useCase: FollowupDoneUseCase)
  extends FollowupsServlet(fakeAuthenticator, new CodebragSwagger, followupFinder, useCase) {
  override def scentry(implicit request: javax.servlet.http.HttpServletRequest) = fakeScentry
}

