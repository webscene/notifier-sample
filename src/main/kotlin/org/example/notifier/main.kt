package org.example.notifier

import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.get
import org.w3c.notifications.NotificationOptions
import org.webscene.client.WebScene as ws

fun main(args: Array<String>) {
    @Suppress("JoinDeclarationAndAssignment")
    val msgTxt: Element?
    @Suppress("JoinDeclarationAndAssignment")
    val showNotificationBtn: Element?

    setupWebPage()
    msgTxt = ws.DomQuery.elementById("msg-txt")
    showNotificationBtn = ws.DomQuery.elementById("show-notification-btn")
    showNotificationBtn?.addEventListener(type = "click", callback = onShowNotificationBtnClick(msgTxt))
    msgTxt.focus()
}

private fun onShowNotificationBtnClick(msgTxt: Element?): (Event) -> Unit {
    return {
        if (msgTxt.txtBoxValue().isNotEmpty()) {
            ws.notification(
                title = "Test Notification",
                options = msgNotificationOptions(msgTxt.txtBoxValue())
            )
        }
    }
}

fun msgNotificationOptions(msg: String) = NotificationOptions(body = msg)

// TODO: Try to find a use for the keyCode extension property.
@Suppress("unused")
val Event.keyCode
    get() = this.asDynamic().keyCode.toString()

fun Element?.focus() {
    if (this?.localName == "input") this.asDynamic().focus()
}

fun Element?.txtBoxValue() = if (this?.localName == "input" && this.attributes["type"]?.value == "text") {
    this.asDynamic().value.toString()
} else {
    ""
}