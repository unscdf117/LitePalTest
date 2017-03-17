package com.unsc.a117.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_message;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到我们关心的控件 添加响应事件的逻辑
        Button bt_createDB = (Button)findViewById(R.id.bt_createDB);
        bt_createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        //找到增删改查的按钮 并且给其添加响应事件的逻辑
        Button bt_add =(Button)findViewById(R.id.bt_add);
        Button bt_del =(Button)findViewById(R.id.bt_del);
        Button bt_update =(Button)findViewById(R.id.bt_update);
        Button bt_query =(Button)findViewById(R.id.bt_query);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建Book类的实例用来增删改查的操作
                Book book = new Book();
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(589);
                book.setPrice(69.00);
                book.setPress("中国邮电出版社");
                book.setName("Android疯狂讲义");
                book.setAuthor("李刚");
                book.setPages(666);
                book.setPrice(69.00);
                book.setPress("山寨出版社");
                book.save();
            }
        });
        //更新操作
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先new出一个Book对象 然后操作Book对象 如果要设置成0请使用default
                Book book = new Book();
                book.setPrice(99.00);
                book.setPress("传智播客");
                //定位操作符合以下标准的条目
                book.updateAll("name = ? and author = ?","第二行代码","郭霖");
            }
        });
        //删
        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里删掉了Book中的id为1的那本书
                DataSupport.deleteAll(Book.class,"id = ?", String.valueOf(1));
                //这里是删掉了其中价格小于80元的书
                DataSupport.deleteAll(Book.class,"price < ?", String.valueOf(80));
                //删除表内所有数据  因为没有约束值 所以全删
                DataSupport.deleteAll(Book.class);
            }
        });
        //查询
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;String author;int pages;double price;String press;
                //一个泛型为Book的List集合的实例books findAll参数写Book.class（业务逻辑参考包内的Book类）就是Book表
                List<Book> books= DataSupport.findAll(Book.class);

                //一个for循环遍历整个表并且将表内的参数实例化
                for(Book book : books){
                    name = book.getName();
                    author = book.getAuthor();
                    pages = book.getPages();
                    price = book.getPrice();
                    press = book.getPress();
                    Toast.makeText(getApplicationContext(),"书名:"+name+" 作者:"+author+" 页数:"+pages+" 价格"+price+"元 出版社:"+press,Toast.LENGTH_LONG).show();

                    //TODO 这里要想办法动态加载数据库的内容到TextView
                }

            }
        });
        tv_message = (TextView)findViewById(R.id.tv_message);
        //设置TextView的内容超出显示范围之后还可以滚动查看
        tv_message.setMovementMethod(new ScrollingMovementMethod());

    }
}
