package com.tencent.qqmusic.api.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.tencent.qqmusic.api.demo.pcm.PcmExampleActivityNew
import kotlinx.android.synthetic.main.activity_example_list.*

/**
 * 4类功能demo分别跳转不同的activity.
 */
class ExampleListActivity : AppCompatActivity() {
    private companion object {
        const val BASE_TEST = "QPlayAidl基本功能及接口测试"
        const val LOGIN_TEST = "QQ音乐登录demo"
        const val PCM_TEST = "Pcm传输demo"
        const val MAIN_TEST = "进MainActivity"
        val BASE_TARGET = VisualActivity::class.java
        val LOGIN_TARGET = LoginExampleActivity::class.java
        val PCM_TARGET = PcmExampleActivityNew::class.java
        val MAIN_TARGET = MainActivity::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_list)

        val exampleList = listOf(
            buildSample(BASE_TEST, BASE_TARGET),
            buildSample(LOGIN_TEST, LOGIN_TARGET),
            buildSample(PCM_TEST, PCM_TARGET),
            buildSample(MAIN_TEST, MAIN_TARGET)
        )
        lv_examples.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exampleList)
        lv_examples.setOnItemClickListener { _, _, position, _ ->
            exampleList[position].action()
        }
    }

    private fun <T : AppCompatActivity> buildSample(test: String, target: Class<T>): Example {
        return Example(test) {
            startActivity(Intent(this, target))
        }
    }
}

class Example(
    private val title: String,
    val action: () -> Unit
) {
    override fun toString(): String {
        return title
    }
}