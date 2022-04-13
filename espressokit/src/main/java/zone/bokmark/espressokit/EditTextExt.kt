package zone.bokmark.espressokit

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ReplaceTextAction
import androidx.test.espresso.action.TypeTextAction


/**
 * 输入文本。
 * 通过调用[EditText]的[setText]方法设置文本。支持中文
 */
fun ViewInteraction.replaceText(text: String) {
    perform(ReplaceTextAction(text))
}


/**
 * 输入文本
 * 通过模拟点击键盘模拟输入，不支持中文。
 */
fun ViewInteraction.typeText(text: String) {
    perform(TypeTextAction(text))
}
