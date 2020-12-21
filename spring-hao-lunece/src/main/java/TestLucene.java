import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;


import java.io.File;
import java.io.IOException;

public class TestLucene {

    @Test
    public void createIndex() throws IOException {

        //1、创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("D:\\luceneIndex").toPath());

        //2、基于Directory对象创建一个IndexWriter对象
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());

        IndexWriter indexWriter = new IndexWriter(directory, config);
        //原始文档的路径
        File files = new File("D:\\lucenefiles\\searchsource");

        for(File file:files.listFiles()){
            String fileName = file.getName();
            String fileContent = FileUtils.readFileToString(file,"utf-8");
            String filePath = file.getPath();
            long fileSize = FileUtils.sizeOf(file);
            //创建文件名域
            //第一个参数：域的名称
            //第二个参数：域的内容
            //第三个参数：是否存储
            Field fileNameField= new TextField("fileName",fileName,Field.Store.YES);
            Field fileContentField= new TextField("fileContent",fileContent,Field.Store.YES);

            Field filePathField= new TextField("filePath",filePath,Field.Store.YES);

            Field fileSizeField= new TextField("fileSize",fileSize+"",Field.Store.YES);

            //创建文档对象
            Document document = new Document();
            document.add(fileNameField);
            document.add(fileContentField);
            document.add(filePathField);
            document.add(fileSizeField);

            //创建索引，并引入索引库
            indexWriter.addDocument(document);

        }
        indexWriter.close();
    }

    @Test
    public void queryIndex() throws IOException {

//        第一步：创建一个Directory对象，也就是索引库存放的位置。
        Directory directory = FSDirectory.open(new File("D:\\luceneIndex").toPath());
//        第二步：创建一个indexReader对象，需要指定Directory对象。
        IndexReader indexReader = DirectoryReader.open(directory);
//        第三步：创建一个indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//        第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
        Query query = new TermQuery(new Term("fileName","新添加的文档"));
//        第五步：执行查询。
        TopDocs topDocs = indexSearcher.search(query,10);
//        第六步：返回查询结果。遍历查询结果并输出。
        System.out.println("查询的结果总数"+topDocs.totalHits);
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("fileName"));
//            System.out.println(document.get("fileContent"));
            System.out.println(document.get("filePath"));
            System.out.println(document.get("fileSize"));
        }
//        第七步：关闭IndexReader对象
        indexReader.close();
    }


    @Test
    public void addDocument() throws Exception {
        //索引库存放路径
        Directory directory = FSDirectory.open(new File("D:\\luceneIndex").toPath());
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        //创建一个indexwriter对象
        IndexWriter indexWriter = new IndexWriter(directory, config);
        //创建一个Document对象
        Document document = new Document();
        //向document对象中添加域。
        //不同的document可以有不同的域，同一个document可以有相同的域。
        document.add(new TextField("fileName", "新添加的文档", Field.Store.YES));
        document.add(new TextField("fileContent", "新添加的文档的内容", Field.Store.NO));
        //LongPoint创建索引
        document.add(new LongPoint("fileSize", 1000l));
        //StoreField存储数据
        document.add(new StoredField("fileSize", 1000l));
        //不需要创建索引的就使用StoreField存储
        document.add(new StoredField("filePath", "D:\\lucenefiles\\searchsource\\1.txt"));
        //添加文档到索引库
        indexWriter.addDocument(document);
        //关闭indexwriter
        indexWriter.close();

    }

}
