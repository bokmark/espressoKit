package zone.bokmark.espressokit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers


/**
 * 点击RecyclerView 的第几个元素
 */
fun ViewInteraction.clickRecyclerViewAtPosition(position: Int) {
    perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position,
            ViewActions.click()
        )
    )
}

/**
 * 点击RecyclerView中包含了content 内容的元素
 */
fun ViewInteraction.clickRecyclerViewWithContext(content: String) {
    perform(
        RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            ViewMatchers.hasDescendant(ViewMatchers.withText(content)),
            ViewActions.click()
        )
    )
}


/**
 * 检查 RecyclerView 总共有几个元素
 */
fun ViewInteraction.checkRecyclerViewItemCount(expectedCount: Int) {
    check(RecyclerViewItemCountAssertion(expectedCount))
}


/**
 * 对RecyclerView的第几个元素，进行检查
 */
fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> =
    object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

/**
 * 检查RecyclerView 的 item 数量
 */
class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        ViewMatchers.assertThat(adapter!!.itemCount, Matchers.`is`(expectedCount))
    }
}
