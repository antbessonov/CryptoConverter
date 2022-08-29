package com.example.cryptoconverter.kaspresso.screen

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

abstract class KTestCase(
    kaspressoBuilder: Kaspresso.Builder = getBuilder(),
) : BaseTestCase<Unit, Unit>(
    kaspressoBuilder = kaspressoBuilder,
    dataProducer = { action -> action?.invoke(Unit) }
) {

    private companion object {

        fun getBuilder(): Kaspresso.Builder = Kaspresso.Builder.simple()
    }
}