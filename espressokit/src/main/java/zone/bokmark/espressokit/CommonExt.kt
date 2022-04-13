package zone.bokmark.espressokit

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers


/**
 * 检查元素是否展示
 */
fun ViewInteraction.checkPresent(isPresent: Boolean) {
    if (isPresent) {
        check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    } else {
        check(ViewAssertions.doesNotExist())
    }
}

/**
 * 检查元素否显示
 */
fun ViewInteraction.checkDisplay(isShow: Boolean) {
    if (isShow) {
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    } else {
        check(ViewAssertions.matches(Matchers.not(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))))
    }
}

/**
 * 点击元素
 */
fun ViewInteraction.click() {
    perform(ViewActions.click())
}
