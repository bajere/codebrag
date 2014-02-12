package com.softwaremill.codebrag.dao.finders.commit

import com.softwaremill.codebrag.dao.reporting.views.{CommitListView, CommitView}
import org.bson.types.ObjectId
import com.softwaremill.codebrag.dao.reviewtask.CommitReviewTaskDAO

trait CommitReviewedByUserMarker {

  def markAsReviewed(commitsViews: List[CommitView], userId: ObjectId) = {
    val remainingToReview = commitReviewTaskDAO.commitsPendingReviewFor(userId)
    commitsViews.map(markIfReviewed(_, remainingToReview))
  }

  def markAsReviewed(commitView: CommitView, userId: ObjectId) = {
    val remainingToReview = commitReviewTaskDAO.commitsPendingReviewFor(userId)
    markIfReviewed(commitView, remainingToReview)
  }

  private def markIfReviewed(commitView: CommitView, remainingToReview: Set[ObjectId]) = {
    if (remainingToReview.contains(new ObjectId(commitView.id)))
      commitView
    else
      commitView.copy(pendingReview = false)
  }

  def commitReviewTaskDAO: CommitReviewTaskDAO
}
