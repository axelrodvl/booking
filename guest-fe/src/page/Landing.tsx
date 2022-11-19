import React from "react";
import {Card, Col, Layout, Row, Typography} from "antd";
import {Content, Footer, Header} from "antd/es/layout/layout";
import { HomeOutlined } from '@ant-design/icons';
import Meta from "antd/es/card/Meta";
const { Title } = Typography;

function Landing() {
    return (
        <div className="App" id="top">
            <Layout className="layout">
                <Header style={{position: 'sticky', top: 0, zIndex: 1, width: '100%', backgroundColor: "#fff", paddingInline: "1rem", boxShadow: "0 2px 8px #f0f1f2"}}>
                    <h1><HomeOutlined/> Booking MVP</h1>
                </Header>
                <Content style={{ padding: '1rem 1rem', backgroundColor: "#fff" }}>
                    <div className="site-layout-content">
                        <Row justify="center" align="middle" style={{marginBottom: "1rem"}}>
                            <Col>
                                <h1>Book your home for your next trip</h1>
                            </Col>
                        </Row>
                        <Row gutter={16} justify="center">
                            <Col
                                xs={24}
                                sm={24}
                                md={18}
                                lg={12}
                                xl={8}
                                xxl={8}
                                style={{marginBottom: "1rem"}}
                            >
                                <Card
                                    cover={
                                        <img
                                            alt="example"
                                            src="images/berlin.jpg"
                                        />
                                    }
                                >
                                    <Meta
                                        title="Cozy apartment in the center of Berlin"
                                        description="Berlin, Germany"
                                    />
                                    <Title level={5} style={{marginBottom: 0}}>50 €/day</Title>
                                </Card>
                            </Col>
                            <Col
                                xs={24}
                                sm={24}
                                md={18}
                                lg={12}
                                xl={8}
                                xxl={8}
                                style={{marginBottom: "1rem"}}
                            >
                                <Card
                                    cover={
                                        <img
                                            alt="example"
                                            src="images/munich.jpg"
                                        />
                                    }
                                >
                                    <Meta
                                        title="Large home in Bavaria"
                                        description="Munich, Germany"
                                    />
                                    <Title level={5} style={{marginBottom: 0}}>400 €/day</Title>
                                </Card>
                            </Col>
                        </Row>
                    </div>
                </Content>
                <Footer style={{ textAlign: 'center' }}>Booking by <a href="https://axelrod.co">axelrod.co</a></Footer>
            </Layout>
        </div>
    );
}

export default Landing;
